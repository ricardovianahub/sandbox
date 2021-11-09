import React from 'react';
import {shallow, mount, render} from 'enzyme';
import jasmineEnzyme from 'jasmine-enzyme'
import App from './App'

describe("App", function () {
    beforeEach(() => {
        jasmineEnzyme()
    });

    it("contains jasmineEnzyme spec with an expectation", function () {
        expect(true).toBe(true);
    });

    it("exists", function () {
        expect(shallow(<App />).exists()).toBeTrue();
    });

    it("has className App", function () {
        expect(shallow(<App />).props().className).toEqual("App");
    });

    it("contains header", function () {
        const app = shallow(<App />);
        console.log(app.find("a").debug());
    });

});
