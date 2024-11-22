import NextAuth from "next-auth";
import { options } from "./authOption";

// eslint-disable-next-line @typescript-eslint/no-unsafe-assignment -- next-auth is
const handler = NextAuth(options);

export { handler as GET, handler as POST };
