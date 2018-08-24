declare module 'react-native-kiper-contacts' {
  export function getAll(
    limit?: number | undefined,
    offset?: number | undefined,
    promise?: (error?: any) => void
  ): void;

  export function searchContacts(
    filters?: [Filter] | undefined,
    limit?: number | undefined,
    offset?: number | undefined,
    promise?: (error?: any) => void
  ): void;

  export interface Contact {
    contactId: String;
    name: String;
    phones: [String];
  }

  export interface Filter {
    key: String;
    value: String;
    type: String;
  }
}
