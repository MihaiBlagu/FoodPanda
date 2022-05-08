import React from 'react'
import "./../css/PopupSendEmail.css"
import Popup from "./Popup";
import "./../css/Popup.css"
import { useNavigate } from "react-router-dom"
import { useState } from "react";

const PopupSendEmail = ({trigger, setPopup, title, message, admin, setDisplayedRest, fetchItems}) => {

    //const navigate = useNavigate()
    const [warningPopup, setWarningPopup] = useState({active: false, title: "", message: ""});
    const [restName, setRestName] = useState("")
    const [restAdded, setAdded] = useState(false)



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

export default PopupSendEmail