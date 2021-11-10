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

    it('should have a key to number 1', function() {
        expect(calculator.find('div.one').length).toEqual(1);
    });

});
