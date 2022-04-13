
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import './App.css';
import AdminLogin from './pages/AdminLogIn'
import CustomerLogIn from './pages/CustomerLogIn'
import CustomerPage from './pages/CustomerPage'
import CustomerCart from './pages/CustomerCart'
import AdminPage from './pages/AdminPage'
import OrderHistory from "./pages/OrderHistory";

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<CustomerLogIn />}/>
        <Route exact path="/admin" element={<AdminLogin />}/>
        <Route exact path="/cust-page/restaurants" element={<CustomerPage />}/>
        <Route exact path="/cust-page/cart" element={<CustomerCart />}/>
        <Route exact path="/admin-page" element={<AdminPage />}/>
        <Route exact path="/cust-page/order-history" element={<OrderHistory />}/>
      </Routes>
    </Router>
  );
}

export default App;
