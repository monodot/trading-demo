// Generates a fake Trade object for POSTing to the API

import { faker } from '@faker-js/faker/locale/en_US';

export const generateTrade = () => ({
    customer: faker.name.fullName(),
    action: faker.helpers.arrayElement(['BUY', 'SELL']),
    asset: faker.helpers.arrayElement(['USD', 'EUR', 'GBP', 'JPY', 'AUD', 'CAD', 'CHF']),
    units: faker.random.numeric()
});
