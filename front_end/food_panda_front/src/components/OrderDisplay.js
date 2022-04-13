import React from 'react'
import { useNavigate, useLocation } from "react-router-dom"
//import "./../css/WelcomePage.css";
import { useState } from "react";
import "./../css/MenuItemDisplay.css"
const { forwardRef, useRef, useImperativeHandle } = React;

const OrderDisplay = ({order, onChangeOrderStatus}) => {

    const [status, setStatus] = useState(0)
    
    const advance = (e) => {
        e.preventDefault()
   
    }

    const decline = (e) => {
        e.preventDefault()
        
    }

    const addItemToCart = (e) => {
        e.preventDefault()

        //onAddItem({item, nbOfItems})
    }

    const textFromStatus = (status) => {
        switch(status) {
            case 0:
                return "PENDING"
            case 1:
                return "ACCEPTED"
            case 2:
                return "IN DELIVERY"
            case 3:
                return "DELIVERED"
            case 4:
                return "DECLINED"
            
            default:
                return ""
        }
    }

    return (
        <div >
            <ul className='item-display'>
                <li className='item-info'><p>{order.orderId}</p></li>
                <li className='item-info'><p>{textFromStatus(order.orderStatus)}</p></li>
                {/* <li className='item-info'><p>price: {item.price}</p></li>
                <li className='item-info'><button onClick={(e) => increment(e)}>+</button></li>
                <li className='item-info'><p>{nbOfItems}</p></li>
                <li className='item-info'><button onClick={(e) => decrement(e)}>-</button></li>
                {nbOfItems > 0 ? <li className='item-info'><button onClick={(e) => addItemToCart(e)}>Add To Cart</button></li>
                : <li className='item-info'><button disabled>Add To Cart</button></li>} */}
                
            </ul>
        </div>
    );
}

export default OrderDisplay
