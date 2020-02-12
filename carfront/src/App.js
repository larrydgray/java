import React, { Component } from 'react';

import './App.css';
import AppBar from '@material-ui/core/AppBar';
import ToolBar from '@material-ui/core/Toolbar';
import Login from './components/Login';



class App extends Component{
  render(){
    return (
      <div className="App">
        <AppBar position="static" color="default">
       
          <ToolBar>Carlist</ ToolBar>
        </AppBar>
        <Login />
      </div>
      

    );
  }
}

export default App;