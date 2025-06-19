import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {
  await page.goto('localhost:9000/projects', {waitUntil: "domcontentloaded"});
});

test('has title', async ({ page }) => {
  await expect(page).toHaveTitle(/Projects | Timlah's Techs/);
});
test('hides the 0th element on click', async ({ page }) => {
  const heading = page.locator('h2#heading0');
  const button = heading.locator('button');
  await button.click();
  await expect(button).toContainClass('collapsed');
});
test('reveals the 1st element on click', async ({ page }) => {
  const heading = page.locator('h2#heading1');
  const button = heading.locator('button');
  await button.click();
  await expect(button).not.toContainClass('collapsed');
});
test('reveals the 1st element on click hides, 0th element on second click', async ({ page }) => {
  const headingZero = page.locator('h2#heading0');
  const headingOne  = page.locator('h2#heading1');
  const buttonZero  = headingZero.locator('button');
  const buttonOne   = headingOne.locator('button');
  await buttonOne.click();
  await buttonZero.click();
  await expect(buttonZero).toContainClass('collapsed');
  await expect(buttonOne).not.toContainClass('collapsed');
});