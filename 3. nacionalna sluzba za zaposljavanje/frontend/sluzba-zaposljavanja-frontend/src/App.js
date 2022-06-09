import logo from './logo.svg';
import './App.css';
import GradjaniComponent from './components/GradjaniComponent';
import OglasiComponent from './components/OglasiComponent';
import Navbar from './navbar/Navbar';
import { BrowserRouter as Router,Routes, Route, Link } from 'react-router-dom';
import ViewOglasComponent from './components/ViewOglasComponent';


function App() {
  return (
    <>
    
      <Router>
      <Navbar />
           
           <Routes>
                 <Route exact path='/gradjani' element={< GradjaniComponent />}></Route>
                 <Route exact path='/oglasi' element={< OglasiComponent />}></Route>
                <Route exact path='/viewOglas/:id' element={ <ViewOglasComponent />}></Route>

          </Routes>
          
       </Router>
    </>
  );
}

export default App;
