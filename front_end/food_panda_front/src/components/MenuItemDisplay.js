import React from 'react'
import { useNavigate, useLocation } from "react-router-dom"
//import "./../css/WelcomePage.css";
import { useState } from "react";
import "./../css/MenuItemDisplay.css"
const { forwardRef, useRef, useImperativeHandle } = React;

const MenuItemDisplay = ({item, onAddItem}) => {
    
    const [nbOfItems, setNbOfItems] = useState(0)
    
    const increment = (e) => {
        e.preventDefault()
   
        setNbOfItems(nbOfItems + 1)
    }

    const decrement = (e) => {
        e.preventDefault()
        
        if(nbOfItems != 0){
            setNbOfItems(nbOfItems - 1)
        }
    }

    const addItemToCart = (e) => {
        e.preventDefault()

        onAddItem({item, nbOfItems})
    }

    const textFromCategory = (category) => {
        switch(category) {
            case 0:
                return "BREAKFAST"
            case 1:
                return "LUNCH"
            case 2:
                return "DINNER"
            case 3:
                return "DESSERT"
            case 4:
                return "BEVERAGE"
            
            default:
                return ""
        }
    }

    return (
        <div >
            <ul className='item-display'>
                <li className='item-info'><p>{textFromCategory(item.category)}</p></li>
                <li className='item-info'><p>{item.title}</p></li>
                <li className='item-info'><p>price: {item.price}</p></li>
                <li className='item-info'><button onClick={(e) => increment(e)}>+</button></li>
                <li className='item-info'><p>{nbOfItems}</p></li>
                <li className='item-info'><button onClick={(e) => decrement(e)}>-</button></li>
                {nbOfItems > 0 ? <li className='item-info'><button onClick={(e) => addItemToCart(e)}>Add To Cart</button></li>
                : <li className='item-info'><button disabled>Add To Cart</button></li>}
                
            </ul>
        </div>
    );
}

export default MenuItemDisplay
