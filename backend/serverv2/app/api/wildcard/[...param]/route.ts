import { NextResponse } from "next/server";
import { connectToDatabase } from "@/lib/mongodb";


export async function GET(req: Request, { params }: { params: { param: string[] } }) {
  await connectToDatabase();

  const { param } = params;
  if (!param || param.length === 0) {
    return NextResponse.json({ message: "Parameter is required" }, { status: 400 });
  }

  // Join all segments into a single string
  const paramString = param.join("/");  
  console.log(`paramString is ${paramString}`);

  return NextResponse.json({ uri: paramString, content: "This is a wildcard path", empty: true, rgdm:'yup', blah:true });
}
