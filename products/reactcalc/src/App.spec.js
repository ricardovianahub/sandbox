import React from 'react';
import {shallow, mount, render} from 'enzyme';
import jasmineEnzyme from 'jasmine-enzyme'
import App from './App'
import Calculator from './components/calculator/Calculator'

describe("App", function () {
    let app;

    beforeEach(() => {
        jasmineEnzyme();
        app = shallow(<App/>);
    });

    it("has className App", function () {
        expect(app.props().className).toEqual("App");
    });

    it("should contain a Calculator component", function () {
        expect(app.containsMatchingElement(<Calculator/>)).toEqual(true);
    });

});
