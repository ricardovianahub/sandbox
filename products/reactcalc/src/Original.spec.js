import React from 'react';
import {shallow, mount, render} from 'enzyme';
import jasmineEnzyme from 'jasmine-enzyme'
import Original from './Original'

describe("Original", function () {
    let original;

    beforeEach(() => {
        jasmineEnzyme();
        original = shallow(<Original />);
    });

    it("contains jasmineEnzyme spec with an expectation", function () {
        expect(true).toBe(true);
    });

    it("exists", function () {
        expect(original.exists()).toBeTrue();
    });

    it("has className App", function () {
        expect(original.props().className).toEqual("Original");
    });

    it("has header with className App-header", function () {
        expect(original.find('header.App-header').length).toEqual(1);
    });

});
