import React from 'react';

class Calculator extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="Calculator">
                <div className="one"></div>
                <div className="two"></div>
            </div>
        );
    }
}

export default Calculator;
