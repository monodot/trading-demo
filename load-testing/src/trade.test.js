import http from 'k6/http';
import { sleep } from 'k6';
import { generateTrade } from './generators/trade';

export const options = {
    vus: 5, // virtual users
    duration: '180s',
};

export default function () {
    const trade = generateTrade();
    const payload = JSON.stringify(trade);

    const result = http.post('http://localhost:8080/trades', payload, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
}
