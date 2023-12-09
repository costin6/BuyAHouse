import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import TokenManager from "../APIs/TokenManager";

const PrivateRouter = () => {
  const userInfo = TokenManager.getAccessToken();

  return userInfo ? <Outlet /> : <Navigate to="/accounts/tokens" />;
};

export default PrivateRouter;