import { NextResponse } from "next/server";

import { GlobalSettingsDTO, ExpirationOption} from "@/dto/global-seetings";




export async function GET() {

    const expirationOptions: ExpirationOption[] = [
        { label: "Destroy when Viewed", value: 0 },
        { label: "Destroy in 1 Minute", value: 60 },
        { label: "Destroy in 10 Minutes", value: 600 },
        { label: "Destroy in 1 Hour", value: 3600 },
        { label: "Destroy in 1 Day", value: 86400 },
        { label: "Destroy in 1 Week", value: 604800 },
        { label: "Destroy in 1 Month", value: 2592000 },
      ];

    const data: GlobalSettingsDTO = {
    siteName: "cl1p.net",
    expirationOptions
  };

  return NextResponse.json(data);
}
