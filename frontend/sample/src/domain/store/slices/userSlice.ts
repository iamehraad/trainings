import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { User } from "@/domain/types/User.types";

export interface UserState {
  user: User | null;
  loadingStatus: "idle" | "loading" | "failed";
  httpError: string | undefined;
}

const initialState: UserState = {
  user: null,
  loadingStatus: "idle",
  httpError: undefined,
};

export const fetchLoginUserAsync = createAsyncThunk(
  "model/fetchLoginUser",
  async (args: { email: string; password: string }) => {
    console.log(args);
    return "";
  },
);

export const userSlice = createSlice({
  name: "model",
  initialState,
  reducers: {
    sampleReducer: (state) => {
      state.httpError = undefined;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchLoginUserAsync.pending, (state) => {
        state.loadingStatus = "loading";
      })
      .addCase(fetchLoginUserAsync.fulfilled, (state, action) => {
        state.loadingStatus = "idle";
        console.log(action.payload);
      })
      .addCase(fetchLoginUserAsync.rejected, (state, action) => {
        state.loadingStatus = "failed";
        state.httpError = action.error.message;
      });
  },
});

export const selectUser = (state: RootState) => state.user;
export const { sampleReducer } = userSlice.actions;
export default userSlice.reducer;
