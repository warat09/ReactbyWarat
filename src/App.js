import React from 'react';
import './App.css';
import Nav from './Nav';
import GB from './GB';
import Graph from './Graph';
import csvtojson from './csvtojson'
import Home from './Home'
import { BrowserRouter as Router, Switch , Route} from 'react-router-dom';

  class App extends React.Component {
    

    constructor(props) {
      super(props);
      this.state={apiResponse:""};
    }
    callAPI(){
      fetch("http://localhost:9000/testAPI")
      .then(res => res.text())
      .then(res => this.setState({apiResponse: res}));
    }

    componentWillMount(){
      this.callAPI();
    }
  
    render(){
      return(
        <Router>
          <div className="App">
            <Nav />
            <Switch>
            <Route path ="/" exact component ={Home} />
            <Route path ="/100GB" component ={GB} />
            <Route path ="/Graph" component ={Graph} />
            <Route path ="/ReadCsv" component ={csvtojson} />
            </Switch>
          </div>
        </Router>
        );
    }
  }
  
export default App;
