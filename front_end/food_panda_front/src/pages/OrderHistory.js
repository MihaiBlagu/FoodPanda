import { useNavigate, useLocation } from "react-router-dom"
import { useState } from "react";
import "./../css/OrderHistory.css"
import "./../css/CustomerHeader.css"
import CustomerHeader from "./../components/CustomerHeader.js"
import Popup from "../components/Popup";
import React from "react"

const OrderHistory = () => {
    
    const {state} = useLocation()
    
    const user = state.user
    const [popup, setPopup] = useState({active: false, title: "", message: ""});
    const [orders, setOrders] = useState([])
    const [currentRestaurant, setCurrRest] = useState({
        name: state.restaurant.name, 
        restaurantId: state.restaurant.restaurantId,
        menuItems: []
    })
    const [cart, setCart] = useState(state.cart)

    React.useEffect(function effectFunction() {
        fetchOrders();
        //addToCart();
    }, []);

    async function fetchOrders() {
            
        const response = await fetch(`http://localhost:8080/orders/${user.userId}`, {
            method: "GET",
            headers: {
                "Content-type": "application/json"
            }
        })

        const data = await response.json()
        data?.map(async (ord) => (
            ord.items = await fetchItems(ord.orderId)
        ))

        console.log(data)
        setOrders(data)
    }

    async function fetchItems(orderId) {
            
        const response = await fetch(`http://localhost:8080/order-menu-item/${orderId}`, {
            method: "GET",
            headers: {
                "Content-type": "application/json"
            }
        })

        if(!response.ok) {
            console.log("err")
            //set pop up
            return []
        } else {
            const data = await response.json()
            
            // data?.map(({first, second}) => (
            //     {
            //         menuItem: first,
            //         nbOfItems: second
            //     }
            // ))

            // const entry = {
            //     menuItem: data.first,
            //     nbOfItems: data.second
            // }

            return data
        }
    }

    const textFromStatus = (category) => {
        switch(category) {
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
        <div>
            <CustomerHeader currState={{user, currentRestaurant, cart}}/>
            
            <div className="order-list">
                <ul className="orders-in-cart">
                    { orders?.map(({customer, items, orderId, orderStatus}) => (
                        <li>
                            <p className="order-info">You order</p>
                            <p className="order-info">status: {textFromStatus(orderStatus)}</p>
                            { items?.map(({first, second}) => (
                                <p className="order-info">{first.title}</p>
                            ))}
                        </li>
                    ))}
                </ul>
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

export default OrderHistory;