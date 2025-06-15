// @ts-check
import { test, expect } from '@playwright/test';

test('has title', async ({ page }) => {
  await page.goto('localhost:9000/');
  await expect(page).toHaveTitle(/Timlah's Techs/);
});

test('get Contact Me link', async ({ page }) => {
  await page.goto('localhost:9000/');
  await page.getByRole('link', { name: 'Contact Me' }).click();
  await expect(page.getByRole('heading', { name: 'Contact Me' })).toBeVisible();
});

// test('play background animations', async ({ page }) => {
//   await page.goto('localhost:9000/');
//   await page.getByRole('button', { name: 'play' }).click();
//   const animationActive = await page.evaluate(() => { 
//     return document.body.classList.contains('.animate-background'); 
//   });
//   expect(animationActive).toBe(true);
// });
// test('pause background animations', async ({ page }) => {
//   await page.goto('localhost:9000/');
//   await page.getByRole('button', { name: 'pause' }).click();
//   const animationActive = await page.evaluate(() => { 
//     return document.body.classList.contains('.animate-background'); 
//   });
//   expect(animationActive).toBe(false);
// });

// test('get Mastodon link', async ({ page }) => {
//   await page.goto('localhost:9000/');
//   await page.getByLabel("Follow Timlah on Mastodon").click();
//   await expect(page.getByRole('heading', { name: 'Timlah', level: 1})).toBeVisible();
// });
