import React from 'react'
import { useNavigate, useLocation } from "react-router-dom"
//import "./../css/WelcomePage.css";
import { useState } from "react";
import "./../css/RestaurantDisplay.css"
import "./../css/Popup.css"
import MenuItemDisplay from "./MenuItemDisplay.js"

const RestaurantDisplay = ({name, restaurantId, menuItems, onAddToCart}) => {
    
    const [displayedItems, setItems] = useState(menuItems)
    
    const handleAddItemToCart = ({item, nbOfItems}) => {
        
        onAddToCart({item, nbOfItems})
    }

    return (
        <div className='rest-display'>
            <p>{name}</p>
            <ul className='items-display'>
                { menuItems?.map((i) => (
                    <li className="items">
                        <MenuItemDisplay 
                            item={i}
                            onAddItem={({item, nbOfItems}) => handleAddItemToCart({item, nbOfItems})}
                        />
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default RestaurantDisplay
