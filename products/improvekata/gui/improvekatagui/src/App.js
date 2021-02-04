import React, { Component } from 'react'
import axios from 'axios'
import './App.css'

class App extends Component {
    constructor () {
        super()
        this.handleInsertClick = this.handleInsertClick.bind(this)
    }

    handleInsertClick () {
        axios.get('http://localhost:8200/monitor')
            .then(response => console.log(response))
        axios.get('http://localhost:8200/queryAll')
            .then(response => console.log(response));
    }

    render() {
        return (
            <div className="App">
                <hr/>
                <header className="App-header">
                    <p className='title__container'>Improvement Kata</p>
                </header>
                <hr/>
                <textarea rows="12" cols="55"></textarea>
                <textarea rows="12" cols="55"></textarea>
                <br/>
                <textarea rows="12" cols="55"></textarea>
                <textarea rows="12" cols="55"></textarea>
                <br/>
                <div className='button__container'>
                    <button className='button' onClick={this.handleInsertClick}>Insert</button>
                </div>
                <hr/>
                <ul>
                    <li>Version 1</li>
                    <li>Version 2</li>
                    <li>Version 3</li>
                    <li>Version 4</li>
                </ul>
            </div>
        )
    }

}

export default App;
