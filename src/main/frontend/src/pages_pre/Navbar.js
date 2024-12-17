import React from 'react';
import { Link } from 'react-router-dom';

const NavBar = ({ token, onLogout }) => {
    return (
        <nav>
            <ul>
                <li><Link to="/">Home</Link></li>
                {token && (
                    <>
                        <li><Link to="/profile">Profile</Link></li>
                        <li><Link to="/user">User Info</Link></li>
                        <li><Link to="/user/add">Add User</Link></li>
                        <li><Link to="/payment">Payment</Link></li>
                        <li>
                            <a href="/login" onClick={onLogout}>Logout</a>
                        </li>
                    </>
                )}
            </ul>
        </nav>
    );
};

export default NavBar;