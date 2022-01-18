import React from 'react';

class Calculator extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="Calculator">
                <div className="key1">1</div>
                <div className="key2">2</div>
                <div className="key3">3</div>
                <div className="key4">4</div>
                <div className="key5">5</div>
                <div className="key6">6</div>
                <div className="key7">7</div>
                <div className="key8">8</div>
                <div className="key9">9</div>
                <div className="key0">0</div>

                <div className="keyplus">+</div>
                <div className="keyminus">-</div>
                <div className="keymultiplication">x</div>
                <div className="keydivision">/</div>
                <div className="keyclear">C</div>
                <div className="keycleardisplay">CE</div>
            </div>
        );
    }
}

export default Calculator;
