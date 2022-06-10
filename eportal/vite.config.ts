import { defineConfig, loadEnv } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig(({ command, mode }) => {
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) };
  if (command === 'serve') {
    return {
      plugins: [react()],
      server: {
        port: Number(process.env.VITE_PORT),
      },
    };
  } else {
    return {
      plugins: [react()],
      server: {
        port: Number(process.env.VITE_PORT),
      },
    };
  }
});
