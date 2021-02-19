import { render, screen } from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByTestId(/fieldAwesome/i);
  expect(linkElement).toBeInTheDocument();
});
