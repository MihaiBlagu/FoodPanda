import { useNavigate } from "react-router-dom"
//import "./../css/WelcomePage.css";
import { useState } from "react";
import Popup from "./../components/Popup";
import "./../css/LoginForm.css"
import bcrypt from 'bcryptjs'

const AdminLogIn = () => {
    const navigate = useNavigate()
    
    const [username, setUsername] = useState("")
    const [password, setPass] = useState("")
    const [popup, setPopup] = useState({active: false, title: "", message: ""});

    const validateInputSignUp = async () => {
        if(!username) {
            setPopup({active: true, title: "Oops!", 
                message: "Username can't be empty"})
            return false
        }
        if(!password) {
            setPopup({active: true, title: "Oops!", 
                message: "Password can't be empty"})
            return false
        }
        if(username.length < 5 || username.length > 20) {
            setPopup({active: true, title: "Oops!", 
                message: "Username length must be between 5 and 20 characters"})
            return false
        }

        const user = await fetchUserByUsername(username)
        if(user){
            setPopup({active: true, title: "Oops!", 
                message: "Username taken!"})
            return false
        }
        if(password.length < 5 || password.length > 20) {
            setPopup({active: true, title: "Oops!", 
                message: "Password length must be between 5 and 20 characters"})
            return false
        }

        return true
    }

    const validateInputLogin = () => {
        if(!username) {
            setPopup({active: true, title: "Oops!", 
                message: "Username can't be empty"})
            return false
        }
        if(!password) {
            setPopup({active: true, title: "Oops!", 
                message: "Password can't be empty"})
            return false
        }

        return true
    }
    
    const addUser = async (user) => {
        const rest = await fetch(`http://localhost:8080/restaurants`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                name: user.username + "_rest",
                deliveryZones: [],
                menu: []
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
        
        user.restaurant = rest
        // console.log(user)

        await fetch(`http://localhost:8080/admins`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(user)
        })
    }

    const fetchUserByUsername = async (username) => {
        try {
            return await fetch(`http://localhost:8080/admins/${username}`)
            .then(function(response) {
                return response.text();
            }).then(function(data) {
                if(data) {
                    return JSON.parse(data)
                } else {
                    return undefined
                }
            })
        } catch (e) {
            
        }
    }

    const onRegisterClick = async (e) => {
        e.preventDefault()

        let goodInput = await validateInputSignUp()

        if (goodInput) {
            let hashedPassword = bcrypt.hashSync(password, bcrypt.genSaltSync())
            addUser({
                username: username, 
                password: hashedPassword
            })

            setPopup({active: true, title: "Hooray!", 
                    message: "Sign Up successfull!"})

            setUsername("")
            setPass("")
        } 
    }

    const onLoginClick = async (e) => {
        e.preventDefault()

        let goodInput = validateInputLogin();

        if (goodInput) {
            const user = await fetchUserByUsername(username);
            
            // console.log(user)
            // console.log(user.password)
            if (!user) {
                setPopup({active: true, title: "Oops!", 
                    message: "Invalid username"})
                return
            } else if (bcrypt.compareSync(password, user.password)){
                navigate('/admin-page', {
                    state: {
                        user: user
                    }
                })
            } else {
                setPopup({active: true, title: "Oops!", 
                    message: "Invalid password"})
            }
        }
    }
    
    return (
        <div>
            <p>THIS PAGE IS FOR ADMINS ONLY</p>
            <div className="log-container">
                <label for="uname"><b>Username</b></label>
                <input
                    className="login-form" 
                    type="text" 
                    placeholder="Enter Username" 
                    value={username} 
                    onChange={(e) => setUsername(e.target.value)}
                />

                <label for="psw"><b>Password</b></label>
                <input 
                    className="login-form"
                    type="password" 
                    placeholder="Enter Password" 
                    value={password} 
                    onChange={(e) => setPass(e.target.value)}
                />

                <button 
                    className="log-btn" 
                    type="submit" 
                    onClick={(e) => onLoginClick(e)}
                >Login</button>
                <button 
                    className="log-btn" 
                    type="submit"
                    onClick={(e) => onRegisterClick(e)}
                >Register</button>

            </div>
            <Popup 
                trigger={popup.active}
                setPopup={setPopup}
                title={popup.title}
                message={popup.message}
            />
        </div>
    )
}

export default AdminLogIn
