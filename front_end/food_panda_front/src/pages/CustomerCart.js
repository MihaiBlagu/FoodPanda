import { useNavigate, useLocation } from "react-router-dom"
import "./../css/CustomerCart.css";
import { useState } from "react";
import "./../css/CustomerCart.css"
import "./../css/CustomerHeader.css"
import CustomerHeader from "./../components/CustomerHeader.js"
import Popup from "../components/Popup";

const CustomerCart = () => {
    
    const {state} = useLocation()
    
    const user = state.user
    const [popup, setPopup] = useState({active: false, title: "", message: ""});
    const [restaurants, setRestaurants] = useState([])
    const [currentRestaurant, setCurrRest] = useState({
        name: state.restaurant.name, 
        restaurantId: state.restaurant.restaurantId,
        menuItems: [],
        deliveryZones: []
    })
    const [cart, setCart] = useState(state.cart)

    const removeFromOrder = ({item, nbOfItems}) => {
        setCart(cart.filter((el) => 
            el.item.title !== item.title
        ))
    }

    const getTotal = () => {
        let total = 0;
        cart.map(({item, nbOfItems}) => (
            total += item.price * nbOfItems
        ))

        return total;
    }

    const placeOrder = async (e) => {
        e.preventDefault()
        
        //post order with menuItems to order controller
        const order = await fetch(`http://localhost:8080/orders`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                customer: user,
                orderStatus: 0,
                restaurant: currentRestaurant
            })
        }).then(function(response) {
            return response.json();
        }).then(function(data) {
            return data  
        })

        //post associations with item id, order id, quantity
        let allOk = true
        await cart?.map(async ({item, nbOfItems}) => (
            await fetch(`http://localhost:8080/order-menu-item`, {
                method: "POST",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify({
                    order: order,
                    menuItem: item,
                    nbOfItems: nbOfItems
                })
            }).then((response) => {
                if(!response.ok){
                    allOk = false
                }
            })
        ))

        if(allOk) {
            setCart([])
        } else {
            setPopup({active: true, title: "Oops!", 
                message: "Some kind of erorr occured!"})
        }
    }

    return (
        <div>
            <CustomerHeader currState={{user, currentRestaurant, cart}}/>
            
            <div className="item-list">
                <ul className="items-in-cart">
                    { cart?.map(({item, nbOfItems}) => (
                        <li>
                            <p className="item-info">{item.title}</p>
                            <p className="item-info">quantity: {nbOfItems}</p>
                            <button 
                                className="item-info"
                                onClick={(e) => removeFromOrder({item, nbOfItems})}
                            >Remove From Order</button>
                        </li>
                    ))}
                </ul>
            </div>
            <ul className="check-out">   
                <li><p>total: {getTotal()}</p></li>
                <li><button onClick={(e) => placeOrder(e)}>Place order</button></li>
            </ul>

            <Popup 
                trigger={popup.active}
                setPopup={setPopup}
                title={popup.title}
                message={popup.message}
            />
        </div>
    );
}

export default CustomerCart;