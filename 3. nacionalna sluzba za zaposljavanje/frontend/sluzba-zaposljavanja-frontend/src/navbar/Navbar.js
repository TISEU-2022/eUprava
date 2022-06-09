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
                  activeStyle={{ color:'black' }}
                >
                    Home
                </NavLink>
                <NavLink 
                  to="/gradjani" 
                  activeStyle={{ color: 'black' }}
                >
                    Gradjani
                </NavLink>
                <NavLink 
                  to="/oglasi" 
                  activeStyle={{ color: 'black' }}
                >
                    Oglasi
                </NavLink>
                <NavLink 
                  to="/login" 
                  activeStyle={{ color: 'black' }}
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