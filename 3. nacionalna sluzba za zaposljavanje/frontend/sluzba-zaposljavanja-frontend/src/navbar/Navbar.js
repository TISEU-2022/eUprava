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

const performLogin = () => {
  window.open(
    `${process.env.REACT_APP_AUTH_SERVER_URL}/auth/login?successUrl=${process.env.REACT_APP_ROOT_URL}/auth/token_handler`,
    '_self',
  );
};

const performLogout = () => {
  window.open(
    `${process.env.AUTH_SERVER_URL}/auth/logout?successUrl=${process.env.ROOT_APP_URL}/auth/logout_handler`,
    '_self',
  );
};

const Navbar = () => {
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
                <NavLink 
                  to="/gradjani" 
                  activestyle={{ color: 'black' }}
                >
                    Gradjani
                </NavLink>
                <NavLink 
                  to="/oglasi" 
                  activestyle={{ color: 'black' }}
                >
                    Oglasi
                </NavLink>
                <NavLink 
                  to=""
                  activestyle={{ color: 'black' }}
                  id="loginNavbar"
                  onClick={performLogin}
                >
                    Login
                </NavLink>
                <NavBtn>
                    <NavBtnLink to="/sign-up">Sign Up</NavBtnLink>                
                </NavBtn>
            </NavMenu> 
           </Nav> 
        </>
  )
}


export default Navbar