import React from 'react'
import "./../css/PopupAddRestaurant.css"
import Popup from "./Popup";
import "./../css/Popup.css"
import { useNavigate } from "react-router-dom"
import { useState } from "react";

const PopupAddRestaurant = ({trigger, setPopup, title, message, admin, setDisplayedRest, fetchItems}) => {

    //const navigate = useNavigate()
    const [warningPopup, setWarningPopup] = useState({active: false, title: "", message: ""});
    const [restName, setRestName] = useState("")
    const [restAdded, setAdded] = useState(false)

    const onSaveClick = async (e) => {
        e.preventDefault()

        const rest = await fetchRestByName()

        if (restName === "") {
            setWarningPopup({active: true, title: "Oops!", 
                message: "Can't leave restaurant name empty!"})
        } else if (rest) {
            console.log(rest)
            setWarningPopup({active: true, title: "Oops!", 
                message: "Restaurant name already taken!"})
        } else {
            await saveRestaurant();
        }
    }

    const saveRestaurant = async () => {
        await fetch(`http://localhost:8080/restaurants/${admin.restaurant.restaurantId}`, {
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                name: restName,
                deliveryZones: [],
                menu: []
            })
        }).then(function(response) {
            return response.text();
        }).then(function(data) {
            if(data) {
                console.log("here: ", JSON.parse(data))
                return JSON.parse(data)

            } else {
                return undefined
            }
        }).then(
            setWarningPopup({
                active: true,
                title: "Succes",
                message: "Restaurant sucessfully added!"
            })
        ).then(
            setAdded(true)
        )
        
        let rest = await fetchRestByName()

        console.log(rest)
        setDisplayedRest(rest)
        fetchItems()

        // await fetch(`http://localhost:8080/admins/${admin.userId}`, {
        //     method: "PUT",
        //     headers: {
        //         "Content-type": "application/json"
        //     },
        //     body: JSON.stringify({
        //         username: admin.username,
        //         password: admin.password,
        //         restaurant: rest
        //     })
        // }).then(
        //     setWarningPopup({
        //         active: true,
        //         title: "Succes",
        //         message: "Restaurant sucessfully added!"
        //     })
        // ).then(
        //     setAdded(true),
        //     setDisplayedRest(rest)
        // )
    }

    const fetchRestByName = async () => {
        return await fetch(`http://localhost:8080/restaurants/${restName}`)
        .then(function(response) {
            return response.text();
        }).then(function(data) {
            if(data) {
                //console.log("here: ", JSON.parse(data))
                return JSON.parse(data)
            } else {
                //console.log("nothing")
                return undefined
            }
        });
    }

    const onExitClick = (e) => {
        e.preventDefault()

        setPopup({active: false})
    }

    return ( trigger ) ? (
        <div className='popup-add-rest'>
            <div className='popup-add-rest-inner'>
                <p>{title}</p>
                <p>{message}</p>
                <p>Restaurant name: </p>
                <input
                    className="rest-name"
                    type='text'
                    autoFocus
                    onChange={(e) => setRestName(e.target.value)}
                />
                { !restAdded ?
                    <button 
                        className="save-btn" 
                        type="submit"
                        onClick={(e) => onSaveClick(e)}
                    >Save</button>
                    :
                    <button 
                        className="save-btn" 
                        type="submit"
                        onClick={(e) => onExitClick(e)}
                    >Exit</button>
                }

                <Popup 
                    trigger={warningPopup.active}
                    setPopup={setWarningPopup}
                    title={warningPopup.title}
                    message={warningPopup.message}
                />
            </div>
        </div>
    ) : "";
}

export default PopupAddRestaurant