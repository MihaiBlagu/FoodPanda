//import "./../css/WelcomePage.css";
import { useState } from "react";
import { useLocation, Link, useNavigate } from "react-router-dom"
import "./../css/CustomerHeader.css"


const CustomerHeader = ({currState}) => {
    const navigate = useNavigate()

    // const {state} = useLocation()
    // const user = state.user

    const navToRestaurants = (e) => {
        e.preventDefault();
        navigate('/cust-page/restaurants', {
            state: {
                user: currState.user,
                restaurant: currState.currentRestaurant,
                cart: currState.cart
            }
        })
    }

    const navToCart = (e) => {
        e.preventDefault();
        navigate('/cust-page/cart', {
            state: {
                user: currState.user,
                restaurant: currState.currentRestaurant,
                cart: currState.cart
            }
        })
    }

    const navToOrderHistory = (e) => {
        e.preventDefault();
        navigate('/cust-page/order-history', {
            state: {
                user: currState.user,
                restaurant: currState.currentRestaurant,
                cart: currState.cart
            }
        })
    }


    const onLogOutClick = (e) => {
        e.preventDefault()
        navigate('/')
    }

    return (
        <div>
            <header className='main-header'>
                <p className='logo'>
                    <a href='/cust-page/restaurants'>FoodPanda</a>
                </p>

                <nav className='nav-bar'>
                    <ul className="nav-bar-list">
                        <li className='nav-item'>
                            <a 
                                href="/cust-page/restaurnats" 
                                onClick={(e) => navToOrderHistory(e)}
                            >
                                <p className='text'>Order History</p>
                            </a>
                        </li>
                        <li className='nav-item'>
                            <a 
                                href="/cust-page/restaurnats" 
                                onClick={(e) => navToRestaurants(e)}
                            >
                                <p className='text'>Restaurants</p>
                            </a>
                        </li>
                        <li className='nav-item'>
                            <a 
                                href="/cust-page/cart" 
                                onClick={(e) => navToCart(e)}
                            >
                                <p className='text'>Cart</p>
                            </a>
                        </li>
                        <li className='nav-item'>
                            <button className='btn'
                                    onClick={(e) => onLogOutClick(e)}
                            >
                                Log Out
                            </button>
                        </li>
                    </ul>
                </nav>

            </header>
        </div>
    );
}

export default CustomerHeader