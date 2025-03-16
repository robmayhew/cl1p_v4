
type ExpirationOption = {
    label: string;
    value: number;
  };

type GlobalSettingsDTO = {
    siteName: string;
    expirationOptions: ExpirationOption[];
}

export type { GlobalSettingsDTO, ExpirationOption };

