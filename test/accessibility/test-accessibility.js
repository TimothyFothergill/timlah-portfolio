const { chromium } = require('playwright');
const { injectAxe, checkA11y } = require('@axe-core/playwright');

(async () => {
  const browser = await chromium.launch();
  const page = await browser.newPage();
  await page.goto('http://localhost:9000'); // Update with your local server URL

  // Inject axe-core into the page
  await injectAxe(page);

  // Run accessibility checks
  await checkA11y(page);

  await browser.close();
})();
