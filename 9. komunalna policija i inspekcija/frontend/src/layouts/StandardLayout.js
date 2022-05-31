import React from 'react';
import Navbar from './../components/Navbar/Navbar';
import Footer from './../components/Footer/Footer';

const standardLayout = props => (
    <>
        <Navbar navLinks={props.navLinks}/>
        <main>
            {props.children}
        </main>
        <Footer/>
    </>
)

export default standardLayout;