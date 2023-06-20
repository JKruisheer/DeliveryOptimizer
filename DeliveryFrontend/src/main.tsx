import React from 'react'
import ReactDOM from 'react-dom/client'

import './index.css'
import {ChakraProvider, theme} from "@chakra-ui/react";
import App from "./App.tsx";

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
      <ChakraProvider theme={theme}>
        <App/>
      </ChakraProvider>
  </React.StrictMode>,
)
