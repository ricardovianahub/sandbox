import React from 'react';
import {shallow, mount, render} from 'enzyme';
import jasmineEnzyme from 'jasmine-enzyme'
import Calculator from './Calculator'

describe("Calculator", function () {
    let calculator;

    beforeEach(() => {
        jasmineEnzyme();
        calculator = shallow(<Calculator/>);
    });

    it("has className App", function () {
        expect(calculator.props().className).toEqual("Calculator");
    });

    it('should have a key to numbers 0-9', function() {
        for (let i = 0; i <= 9; i++) {
            let divs = calculator.find('div.key' + i);
            expect(divs.length).toEqual(1);
            expect(divs.first().text()).toEqual(i.toString());
        }
    });

    it('should have a key to operations + - x / = C CE', function() {
        const opers = [
            ["plus", "+"], ["minus", "-"],
            ["multiplication", "x"], ["division", "/"],
            ["clear", "C"], ["cleardisplay", "CE"]
        ];
        for (let i = 0; i < opers.length; i++) {
            let searchFor = 'div.key' + opers[i][0];
            let div = calculator.find(searchFor).first();
            expect(div.text()).toEqual(opers[i][1]);
        }
    });

});
