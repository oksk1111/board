import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'; 
import ListBoardComponent from './components/ListBoardComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateBoardComponent from './components/CreateBoardComponent';

function App() {
  return (
    <div> 
      <Router>
        <HeaderComponent/>
          <div className="container">
            <Switch>
              <Route path="/" exact component={ListBoardComponent}></Route>
              <Route path="/board" component={ListBoardComponent}></Route>
              <Route path="/create-board" component={CreateBoardComponent}></Route>
            </Switch>
          </div>
        <FooterComponent/>
      </Router>
    </div>
  );
}


export default App;