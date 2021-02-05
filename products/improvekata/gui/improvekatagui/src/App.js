import React, {Component} from 'react'
import axios from 'axios'
import './App.css'

class App extends Component {
    constructor(props) {
        super(props)
        this.handleInsertClick = this.handleInsertClick.bind(this)
        this.field1 = React.createRef();
        this.field2 = React.createRef();
        this.field3 = React.createRef();
        this.field4 = React.createRef();
        axios.get('http://localhost:8200/queryAll')
            .then(response => {
                const ig = response.data[0];
                this.field1.va = ig.field1Awesome;
                this.field2.current = ig.field2Now;
                this.field3.current = ig.field3Next;
                this.field1.current = ig.field4Breakdown;
                console.log('====> queryAll3 <====== ' + ig.teamName + ' <=======')
            });
    }

    handleInsertClick() {
        axios.get('http://localhost:8200/monitor')
            .then(response => console.log(response))
    }

    render() {
        return (
            <div className="App">
                <hr/>
                <header className="App-header">
                    <p className='title__container'>Improvement Kata</p>
                </header>
                <hr/>
                <textarea rows="12" cols="55" ref={this.field1}></textarea>
                <textarea rows="12" cols="55" ref={this.field2}></textarea>
                <br/>
                <textarea rows="12" cols="55" ref={this.field3}></textarea>
                <textarea rows="12" cols="55" ref={this.field4}></textarea>
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

// import axios from 'axios'
// export default {
//     getData: () =>
//         axios({
//             'method':'GET',
//             'url':'https://example.com/query',
//             'headers': {
//                 'content-type':'application/octet-stream',
//                 'x-rapidapi-host':'example.com',
//                 'x-rapidapi-key': process.env.RAPIDAPI_KEY
//             },
//             'params': {
//                 'search':'parameter',
//             },
//         })
// }

// import React from 'react';
//
// import axios from 'axios';
//
// export default class PersonList extends React.Component {
//     state = {
//         persons: []
//     }
//
//     componentDidMount() {
//         axios.get(`https://jsonplaceholder.typicode.com/users`)
//             .then(res => {
//                 const persons = res.data;
//                 this.setState({ persons });
//             })
//     }
//
//     render() {
//         return (
//             <ul>
//                 { this.state.persons.map(person => <li>{person.name}</li>)}
//             </ul>
//         )
//     }
// }

export default App;
