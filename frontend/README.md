# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Oxc](https://oxc.rs)
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/)

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.

## Quick start (project-specific)

Install dependencies:

```bash
cd frontend
npm install
```

Run dev server (for development):

```bash
npm run dev -- --host 0.0.0.0
```

Build production static files:

```bash
npm run build
```

Docker (production image served by nginx):

```bash
# from repository root
docker compose build frontend
docker compose up -d frontend
```

Build-time environment variable:

- `VITE_BACKEND_URL` — backend API URL baked into the static build (default: `http://backend:8080`).
