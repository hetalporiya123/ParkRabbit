import { createRoot } from "react-dom/client";
import "./index.css";
import router from "./routers/Routers";
import { RouterProvider } from "react-router";
import NotificationProvider from "./notifications/NotificationProvider";

createRoot(document.getElementById("root")).render(
  <NotificationProvider>
    {/* <Toaster 
      position="top-right"
      toastOptions={{
        duration:4000,
      }}
    /> */}
    <RouterProvider router={router} />
  </NotificationProvider>
);
