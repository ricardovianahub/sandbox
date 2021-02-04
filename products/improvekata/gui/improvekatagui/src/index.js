import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

class benBootTime extends React.Component {
    bootTimeDescription() {
        fetch('http://localhost:8200/monitor')
            // .then((response) => response.json())
            .then((data) => console.log('This is your data', data));
    }
    render() {
        return <h1>my Component has Mounted, Check the browser 'console' </h1>;
    }
}
export default benBootTime;
