import React, { useRef } from "react";
import { useNavigate, useLocation } from "react-router-dom"
//import "./../css/WelcomePage.css";
import { useState } from "react";
import "./../css/AdminPage.css"
// import "./../css/CustomerHeader.css"
//import CustomerHeader from "./../components/CustomerHeader.js"
//import RestaurantDisplay from "../components/RestaurantDisplay";
import Popup from "./../components/Popup";
import PopupAddRestaurant from "./../components/PopupAddRestaurant";
import OrderDisplay from "../components/OrderDisplay";

const AdminPage = ({}) => {

    const {state} = useLocation()
    const [user, setUser] = useState(state.user)
    const [popup, setPopup] = useState({active: false, title: "h", message: ""});
    const [displayedRest, setDisplayedRest] = useState(user.restaurant)
    const [items, setItems] = useState([])
    const [orders, setOrders] = useState([])
    const [addRestPopup, setRestPopup] = useState({
        active: false, 
        title: "", 
        message: "",
        admin: user
    });
    const [newItemTitle, setNewItemTitle] = useState("")
    const [newCategory, setNewCategory] = useState(0)
    const [newPrice, setNewPrice] = useState(0)
    const [res, setRes] = useState(0)
        
    React.useEffect(function effectFunction() {
        fetchItems()
        fetchOrders()
        
        if (user.restaurant.name === user.username + "_rest") {
            setRestPopup({
                active: true, 
                title: "Looks like you have no restaurant yet!", 
                message: "Create a restaurant now.",
                admin: user
            })
        } else {
            setRestPopup({
                active: false, 
                title: "", 
                message: "",
                admin: user
            })
        }
    }, []);

    
    const fetchItems = async () => {
        await fetch(`http://localhost:8080/menu-items/${displayedRest.restaurantId}`)
        .then(function(response) {
            return response.text();
        }).then(function(data) {
            if(data) {
                //console.log(JSON.parse(data))
                setItems(JSON.parse(data))
            } else {
                return undefined
            }
        });
    }

    const fetchOrders = async () => {
        await fetch(`http://localhost:8080/orders/restaurant/${displayedRest.restaurantId}`)
        .then(function(response) {
            return response.text();
        }).then(function(data) {
            if(data) {
                //console.log(JSON.parse(data))
                setOrders(JSON.parse(data))
            } else {
                return undefined
            }
        });
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

    const saveItem = async (e) => {
        e.preventDefault()

        let ok = true
        if(newItemTitle === ""){
            setPopup({
                active: true, 
                title: "Empty Item Name!", 
                message: "Please type a name for your item",
            })
            ok = false
        }
        if(newPrice === ""){
            setPopup({
                active: true, 
                title: "Empty Price!", 
                message: "Please type a price for your item",
            })
            ok = false
        } 
        if(isNaN(newPrice)){
            setPopup({
                active: true, 
                title: "Price is not a number!", 
                message: "Please type a price that is a number",
            })
            ok = false
        }

        if(ok) {
            const item = await fetch(`http://localhost:8080/menu-items`, {
                method: "POST",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify({
                    title: newItemTitle,
                    price: newPrice,
                    category: newCategory,
                    restaurant: user.restaurant
                })
            }).then(function(response) {
                return response.text();
            }).then(function(data) {
                if(data) {
                    //console.log("here: ", JSON.parse(data))
                    return JSON.parse(data)
                } else {
                    return undefined
                }
            });
        }
    }

    const handleNewOrderStatus = async ({o, newStatus}) => {
        //update order in db
    }

    const generateMenuPdf = async () => {
        let res = await fetch(`http://localhost:8080/admins/pdf`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(items)
        })
        let status = await res.json();
        console.log(status)

        switch(status) {
            case 0:
                setPopup({
                    active: true, 
                    title: "Success", 
                    message: "PDF generated",
                })
                break
            case 1:
                setPopup({
                    active: true, 
                    title: "Failure", 
                    message: "Something went wrong while generating PDF",
                })
                break
            case -1:
                setPopup({
                    active: true, 
                    title: "Oops", 
                    message: "No items have been added to the menu. PDF has not been generated",
                })
                break

            default:
                setPopup({
                    active: true, 
                    title: "Failure", 
                    message: "Something went wrong while generating PDF",
                })
                break
        }
    }

    return (
        <div>
            <div className='col left'>
                <p><b>{displayedRest.name}</b></p>
                <div className="items-display">
                    { items?.map((it) => (
                        <ul className="item">
                            <li className="item-info"><p>{textFromCategory(it.category)}</p></li>
                            <li className="item-info"><p>{it.title}</p></li>
                        </ul>
                    ))}
                </div>
            </div>
            
            <div className='col mid'>
                <p>add new items</p>
                <ul className="item">
                    <li className="item-info">
                        <p>Category: </p>
                    </li>
                    <li className="item-info">
                        <select  onChange={(e) => setNewCategory(e.target.value)} id="cars" name="cars">
                            <option value="0">Breakfast</option>
                            <option value="1">Lunch</option>
                            <option value="2">Dinner</option>
                            <option value="3">Dessert</option>
                            <option value="4">Beverage</option>
                        </select>
                    </li>
                </ul>          
                <ul className="item">
                    <li className="item-info">
                        <p>Title: </p>
                    </li>
                    <li className="item-info">
                        <input 
                            type="text" 
                            placeholder="Name.." 
                            value={newItemTitle} 
                            onChange={(e) => setNewItemTitle(e.target.value)}
                        />
                    </li>
                </ul>
                <ul className="item">
                    <li className="item-info">
                        <p>Price: </p>
                    </li>
                    <li className="item-info">
                        <input 
                            type="text" 
                            placeholder="Price.." 
                            value={newPrice} 
                            onChange={(e) => setNewPrice(e.target.value)}
                        />
                    </li>
                </ul> 
                <button
                    onClick={(e) => saveItem(e)}
                >
                    Save item
                </button>
                <button
                    onClick={() => fetchItems()}
                >
                    Refresh
                </button>
                <br></br>
                <button
                    onClick={() => generateMenuPdf()}
                >
                    Generate Menu PDF
                </button>
            </div>

            <div className='col right'>
                <p>orders</p>
                <ul className='items-display'>
                    { orders?.map((o) => (
                        <li className="items">
                            <OrderDisplay 
                                order={o}
                                onChangeOrderStatus={({o, newStatus}) => handleNewOrderStatus({o, newStatus})}
                            />
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
            <PopupAddRestaurant 
                trigger={addRestPopup.active}
                setPopup={setRestPopup}
                title={addRestPopup.title}
                message={addRestPopup.message}
                admin={addRestPopup.admin}
                setDisplayedRest={setDisplayedRest}
                fetchItems={fetchItems}
            />
        </div>
    );
}

export default AdminPage;