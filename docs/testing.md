# Testing

Before raising a PR, run through these steps.

## Running tests

For unit tests
```shell
sbt test
```

For a11y and end-to-end tests:

```shell
npm install
npm run test:a11y
npm run e2e-test
```

a11y testing done with axe-core
e2e testing done with playwright

## Node.js usage

Node.js usage needs to be kept under check; I use node to automate some tasks, namely some a11y tests and playwright e2e testing.

Before raising a PR, just run a simple audit command:
```shell
npm audit
```
This gives us enough of an idea if any of our packages are compromised.