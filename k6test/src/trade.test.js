import http from 'k6/http';
import { sleep } from 'k6';
import { generateTrade } from './generators/trade';

export const options = {
    vus: 5, // The number of VUs (virtual users) to run concurrently
    duration: '180s', // The length of the test
};

// The main function that will be executed for each virtual user
export default function () {
    const trade = generateTrade();
    const payload = JSON.stringify(trade);

    const baseUrl = __ENV.TRADING_SERVICE_BASE_URL === undefined 
        ? 'http://localhost:8080' 
        : __ENV.TRADING_SERVICE_BASE_URL;

    // POST the object to the trading-service API
    const result = http.post(`${baseUrl}/trades`, payload, {
        headers: {
            'Content-Type': 'application/json'
        }
    });

    sleep(0.25); // Pause for a quarter of a second before continuing
}
