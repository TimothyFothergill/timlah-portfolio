const { chromium } = require('playwright');
const axeSource = require('axe-core').source;

(async () => {
  const endpoints = [
    "/",
    "/about",
    "/contact",
    "/projects",
    "/blog",
    "/latest-blog",
    "/latest-blog",
    "/word-up",
    "/gallery/kitacon-2024",
  ];
  endpoints.forEach(checkPage);
})();

async function checkPage(endpoint) {
  const browser = await chromium.launch();
  const page = await browser.newPage();
  await page.goto('http://localhost:9000' + endpoint);

  await page.evaluate(axeSource);

  const results = await page.evaluate(async () => {
    return await window.axe.run();
  });

  console.log('Accessibility Violations for ' + endpoint + ':', results.violations);
  await browser.close();
}
