import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      // Proxy all /api requests to the backend service during development.
      // This allows frontend code to use relative paths like `/api/auth/register`.
      '/api': {
        target: 'http://backend:8080',
        changeOrigin: true,
        secure: false,
        ws: false,
      },
    },
  },
})
