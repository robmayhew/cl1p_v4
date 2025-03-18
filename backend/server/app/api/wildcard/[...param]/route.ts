import { NextResponse } from "next/server";
import { connectToDatabase } from "@/lib/mongodb";
import { WildcardDTO } from "@/dto/wildcard-dto";


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


export async function POST(req: Request) {
  try {
    const { content, expiration } = await req.json();
    console.log(`content is ${content}`);
    if (!content || !expiration) {
      return NextResponse.json({ error: "Missing required fields" }, { status: 400 });
    }

   //const db = await connectToDatabase();
    // const collection = db.collection("wildcards");

    // const result = await collection.insertOne({
    //   content,
    //   expiration,
    //   createdAt: new Date(),
    // });

    const data: WildcardDTO = {
      uri: "test",
      content,
      empty: true,
      rgdm: 'yup',
      blah: true
    };

    return NextResponse.json(data, { status: 201 });
  } catch (error) {
    console.error("Error saving wildcard:", error);
    return NextResponse.json({ error: "Internal Server Error" }, { status: 500 });
  }
}