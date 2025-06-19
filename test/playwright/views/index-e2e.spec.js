import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {
  await page.goto('localhost:9000/', {waitUntil: "domcontentloaded"});
});

test('has title', async ({ page }) => {
  await expect(page).toHaveTitle(/Timlah's Techs/);
});
test('get Home link', async ({ page }) => {
  await page.getByRole('link', { name: 'Home' }).click();
  await expect(page.getByRole('heading', { name: 'Timlah', level: 1 })).toBeVisible();
});
test('get What I Use link', async ({ page }) => {
  await page.getByRole('link', { name: 'About Me' }).click();
  await page.getByRole('link', { name: 'What I Use' }).click();
  await expect(page.getByRole('heading', { name: 'Tools, Software, Fonts etc', level: 1 })).toBeVisible();
});
test('get Latest Blog link', async ({ page }) => {
  await page.getByRole('link', { name: 'Blog' }).click();
  await page.getByRole('link', { name: 'Latest Blog Post' }).click();
  await expect(page).toHaveURL(new RegExp('.*/blog/slug/.*'));
});
test('get All Blog Posts link', async ({ page }) => {
  await page.getByRole('link', { name: 'Blog' }).click();
  await page.getByRole('link', { name: 'All Blog Posts' }).click();
  await expect(page.getByRole('heading', { name: 'Timlah\'s Tech Blog', level: 1 })).toBeVisible();
});
test('get Kitacon 2024 Gallery link', async ({ page }) => {
  await page.getByRole('link', { name: 'Galleries' }).click();
  await page.getByRole('link', { name: 'Kitacon 2024' }).click();
  await expect(page.getByRole('heading', { name: 'Kitacon 2024', level: 1 })).toBeVisible();
});
test('get Projects link', async ({ page }) => {
  await page.getByRole('link', { name: 'Projects' }).click();
  await expect(page.getByRole('heading', { name: 'Projects', level: 1 })).toBeVisible();
});
test('get Tic-Tac-Toe link', async ({ page }) => {
  await page.getByRole('link', { name: 'Games' }).click();
  await page.getByRole('link', { name: 'Tic-Tac-Toe' }).click();
  await expect(page.getByRole('heading', { name: 'Tic-Tac-Toe', level: 1 })).toBeVisible();
});
test('get Word-Up link', async ({ page }) => {
  await page.getByRole('link', { name: 'Games' }).click();
  await page.getByRole('link', { name: 'Word-Up' }).click();
  await expect(page.getByRole('heading', { name: 'Word-Up', level: 1 })).toBeVisible();
});
test('get Contact Me link', async ({ page }) => {
  await page.getByRole('link', { name: 'Contact Me' }).click();
  await expect(page.getByRole('heading', { name: 'Contact Me' })).toBeVisible();
});
test('play background animations', async ({ page }) => {
  await page.getByRole('button', { name: 'play' }).click();
  const animationActive = await page.evaluate(() => { 
    return document.body.classList.contains('animate-background'); 
  });
  expect(animationActive).toBe(true);
});
test('pause background animations', async ({ page }) => {
  await page.getByRole('button', { name: 'pause' }).click();
  const animationActive = await page.evaluate(() => { 
    return document.body.classList.contains('animate-background'); 
  });
  expect(animationActive).toBe(false);
});
