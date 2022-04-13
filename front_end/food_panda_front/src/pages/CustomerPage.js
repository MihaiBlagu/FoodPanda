import React, { useRef } from "react";
import { useNavigate, useLocation } from "react-router-dom"
//import "./../css/WelcomePage.css";
import { useState } from "react";
import "./../css/CustomerPage.css"
// import "./../css/CustomerHeader.css"
import CustomerHeader from "./../components/CustomerHeader.js"
import RestaurantDisplay from "../components/RestaurantDisplay";
import Popup from "./../components/Popup";

const CustomerPage = ({}) => {

    const {state} = useLocation()
    
    const user = state.user
    const [popup, setPopup] = useState({active: false, title: "", message: ""});
    const [restaurants, setRestaurants] = useState([])
    const [currentRestaurant, setCurrRest] = useState({
        name: state.restaurant.name, 
        restaurantId: state.restaurant.restaurantId,
        menuItems: []
    })
    const [cart, setCart] = useState(state.cart)
    const [searchedRest, setSearchedRes] = useState("")

    const resDisplay = useRef()
    const navigate = useNavigate()

    React.useEffect(function effectFunction() {
        fetchRestaurants();
        //addToCart();
    }, []);

    async function fetchRestaurants() {
            
        const response = await fetch('http://localhost:8080/restaurants', {
            method: "GET",
            headers: {
                "Content-type": "application/json"
            }
        })

        const data = await response.json()
        setRestaurants(data)
    }

    async function fetchMenuItems(name, restaurantId) {
        const response = await fetch(`http://localhost:8080/menu-items/${restaurantId}`, {
            method: "GET",
            headers: {
                "Content-type": "application/json"
            }
        })
        
        if (response) {
            const data = await response.json()
            setCurrRest({name: name, restaurantId: restaurantId, menuItems: data})
        }
    }

    const changeRestaurant = (name, restaurantId) => {

        fetchMenuItems(name, restaurantId)
    }

    const addToCart = ({item, nbOfItems}) => {
        const newCart = cart;

        let sameRestOrNotExsiting = true
        newCart?.map((el) => {
            if(item.restaurant.restaurantId != el.item.restaurant.restaurantId) {
                sameRestOrNotExsiting = false
                setPopup({active: true, title: "Oops!", 
                    message: "Looks like you picked an item from a different restaurant " + 
                    "than your other items! You can only oreder items from one restaurant!"})
            }
            if(el.item.title === item.title){
                el.nbOfItems += nbOfItems
                sameRestOrNotExsiting = false
                setCart(newCart)
            }
        })

        if(sameRestOrNotExsiting) {
            setCart([...newCart, {item, nbOfItems}])
        }
    }

    const searchForRestaurant = async (e) => {
        e.preventDefault()

        if(searchedRest !== "") {
            const rest = await fetch(`http://localhost:8080/restaurants/${searchedRest}`)
            .then(function(response) {
                return response.text();
            }).then(function(data) {
                if(data) {
                    //console.log("here: ", JSON.parse(data))
                    return JSON.parse(data)
                } else {
                    return undefined
                }
            });
    
            if(rest) {
                changeRestaurant(rest.name, rest.restaurantId)
            } else {
                setCurrRest({
                    name: "Restaurant not found", 
                    restaurantId: -1,
                    menuItems: []
                })
            }
        } else {
            setPopup({active: true, title: "Oops!", 
                    message: "Empty Restaurant name"})
        }
    }

    return (
        <div>
            <CustomerHeader currState={{user, currentRestaurant, cart}}/>
            
            <div className="column left">
                <ul className="rest-list">
                    <li className="rest">
                        <input  
                            placeholder="restaurant name..." 
                            value={searchedRest} 
                            onChange={(e) => setSearchedRes(e.target.value)}
                        />
                        <button
                            onClick={(e) => searchForRestaurant(e)}
                        >Search</button>
                    </li>
                    { restaurants?.map(({restaurantId, name}) => (
                        <li className="rest">
                            <p onClick = {(e) => changeRestaurant(name, restaurantId)}>
                                {name}
                            </p>
                        </li>
                    ))}
                </ul>
            </div>
            <div className="column right">
                <RestaurantDisplay 
                    name={currentRestaurant.name}
                    restaurantId={currentRestaurant.restaurantId}
                    menuItems={currentRestaurant.menuItems}
                    onAddToCart={({item, nbOfItems}) => addToCart({item, nbOfItems})}
                />
                {/* <p className="rest-display">{currentRestaurant.name}</p> */}
            </div>

            <Popup 
                trigger={popup.active}
                setPopup={setPopup}
                title={popup.title}
                message={popup.message}
            />
        </div>
    );
}

export default CustomerPage;