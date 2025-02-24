

export interface VaccinationCenter {
    id: number;
    name: string;
    address: {
        street: string;
        city: string;
    };
    postalCode: string;
    openingDate: Date;
}
