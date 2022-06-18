import React from 'react'
import {
    Nav,
    NavLogo,
    NavLink,
    Bars,
    NavMenu,
    NavBtn,
    NavBtnLink,
} from "./NavbarElements";
import { useEffect, useState } from 'react';
import { AuthenticationService, getRole } from '../services/AuthenticationService';





const performLogin = () => {
  window.open(
    `${process.env.REACT_APP_AUTH_SERVER_URL}/auth/login?successUrl=${process.env.REACT_APP_ROOT_URL}/auth/token_handler`,
    '_self',
  );
};

const performEportal = () => {
  window.open(
    `http://localhost:4011/p/portals`,
    '_self',
  );
};

const performLogout = () => {
  window.open(
    `${process.env.AUTH_SERVER_URL}/auth/logout?successUrl=${process.env.ROOT_APP_URL}/auth/logout_handler`,
    '_self',
  );
  AuthenticationService.logout();
};

const Navbar = () => {
  const [role, setRole] = useState('');
  const [username, setUsername] = useState("");

  useEffect(() => {
    let role = AuthenticationService.getRole()
    setRole(role)
    const username = AuthenticationService.getUsername();
    setUsername(username);
    

}, []);

  return (
    <>
           <Nav>
            <NavLogo to="/">
                Logo
            </NavLogo>
            <Bars />

            <NavMenu>
                <NavLink 
                  to="/home" 
                  activestyle={{ color:'black' }}
                >
                    Home
                </NavLink>
                {role == 'biro_sluzbenik' ? (
                <NavLink 
                  to="/gradjani" 
                  activestyle={{ color: 'black' }}
                >
                    Gradjani
                </NavLink>
                ): (<div></div>) }
                <NavLink 
                  to="/oglasi" 
                  activestyle={{ color: 'black' }}
                >
                    Oglasi
                </NavLink>
                {username==null ? (
                <NavLink 
                  to=""
                  activestyle={{ color: 'black' }}
                  id="loginNavbar"
                  onClick={performLogin}
                >
                    Login
                </NavLink>
                ) : 
                (
                    <div></div>
                    )}

                {username!=null ? (
                <NavLink 
                  to=""
                  activestyle={{ color: 'black' }}
                  onClick={performLogout}
                >
                    Logout
                </NavLink>
                ) : 
                (
                    <div></div>
                    )}
                <NavBtn>
                    <NavBtnLink to="" onClick={performEportal}>ePortal</NavBtnLink>                
                </NavBtn>
            </NavMenu> 
           </Nav> 
        </>
  )
}


export default Navbar