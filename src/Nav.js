import React from 'react';
import './App.css';
import { Link } from 'react-router-dom';

  function Nav() {
    return(
      <nav>
          <ul className="nav-links">
            <Link to="/"><li>Home</li></Link>
              <Link to="/100GB"><li>100GB</li></Link>
              <Link to="/Graph"><li>Graph</li></Link>
              <Link to="/ReadCsv"><li>ReadCSV</li></Link>
          </ul>
      </nav>
    );
  }

export default Nav;
