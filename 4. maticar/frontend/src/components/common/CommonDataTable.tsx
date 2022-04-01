import {
  Box,
  BoxProps,
  Button,
  ButtonProps,
  Center,
  Input,
  InputGroup,
  InputGroupProps,
  InputProps,
  InputRightElement,
  Table,
  TableCaption,
  TableContainer,
  TableContainerProps,
  TableProps,
  Tbody,
  Td,
  Text,
  Th,
  Thead,
  Tr,
  useTheme,
} from '@chakra-ui/react';
import React, {
  ChangeEvent,
  Dispatch,
  ReactElement,
  SetStateAction,
  useEffect,
  useMemo,
  useState,
} from 'react';
import { TriangleDownIcon, TriangleUpIcon } from '@chakra-ui/icons';

type SortState = { key?: string; order: 'asc' | 'desc' | 'none' };

export type CommonDataTableHeadings<T> = {
  key: keyof T;
  numeric?: boolean;
  title?: string;
};

export type CommonDataTablePropsWithoutSearch<T> = {
  tableProps?: TableProps;
  tableCaption?: string | ReactElement;
  tableHeadings: CommonDataTableHeadings<T>[];
  tableContainerProps?: TableContainerProps;
  placeholderTableRow?: string | ReactElement;
  wrapInBoxProps?: BoxProps;
  data: T[];
};

export type CommonDataTableProps<T> = CommonDataTablePropsWithoutSearch<T> &
  Partial<{
    disableSearch: boolean;
    searchInputProps: InputProps;
    searchInputGroupProps: InputGroupProps;
    searchButtonProps: ButtonProps;
  }> & { disableSort?: boolean };

const CommonDataTable = <T extends unknown>({
  data: tableData,
  tableHeadings,
  tableContainerProps,
  tableProps,
  tableCaption,
  placeholderTableRow,
  wrapInBoxProps,
  disableSearch,
  disableSort,
  searchInputProps,
  searchInputGroupProps,
  searchButtonProps,
}: CommonDataTableProps<T>) => {
  const [searchTerm, setSearchTerm] = useState<string>('');
  const [sortState, setSortState] = useState<SortState>({
    order: 'asc',
    key: Object.keys((tableData as any[])[0])[0],
  });

  const data = useMemo(() => {
    if (!searchTerm && sortState.order === 'none') return tableData;
    else if (sortState.order === 'none') {
      return searchArray(tableData, searchTerm);
    }
    const { key: sortKey, order: sortOrder } = sortState as typeof sortState & {
      key: string;
    };
    return (searchArray(tableData, searchTerm) as any[]).sort(
      (first, second) => {
        if (first === second) return 0;
        let ret: number;
        if (sortOrder === 'asc')
          return first[sortKey] > second[sortKey] ? 1 : -1;
        else {
          return first[sortKey] < second[sortKey] ? 1 : -1;
        }
        return ret;
      },
    );
  }, [tableData, searchTerm, sortState]);

  const tableCaptionComponent = () => {
    if (!tableCaption) return <></>;
    if (typeof tableCaption === 'string')
      return <TableCaption>{tableCaption}</TableCaption>;
    if (React.isValidElement(tableCaption)) return tableCaption;
  };

  return (
    <BoxWrap wrapping={wrapInBoxProps}>
      {!disableSearch && (
        <SearchComponent
          handler={setSearchTerm}
          value={searchTerm}
          inputGroupProps={searchInputGroupProps}
          inputProps={searchInputProps}
          buttonProps={searchButtonProps}
        />
      )}
      <TableContainer {...tableContainerProps}>
        <Table {...tableProps}>
          {data.length ? (
            <>
              {tableCaptionComponent()}
              <Thead>
                <Tr>
                  <TheadsComponent
                    disableSort={disableSort}
                    sortState={sortState}
                    setSortState={setSortState}
                    tableHeadings={tableHeadings}
                  ></TheadsComponent>
                </Tr>
              </Thead>
              <Tbody>
                <TbodyComponent
                  placeHolderTableRow={placeholderTableRow}
                  data={data}
                  tableHeadings={tableHeadings}
                ></TbodyComponent>
              </Tbody>
            </>
          ) : (
            <Center padding={2}>No data entries!</Center>
          )}
        </Table>
      </TableContainer>
    </BoxWrap>
  );
};

export type ValueHandler<T> = (value: T) => void;
export type Handler<T> = Dispatch<SetStateAction<T>> | ValueHandler<T>;

const SearchComponent: React.FC<{
  value: string;
  handler: Handler<string>;
  inputProps?: InputProps;
  buttonProps?: ButtonProps;
  inputGroupProps?: InputGroupProps;
}> = ({ value, handler, inputGroupProps, inputProps, buttonProps }) => {
  const handleClick = () => handler('');
  const searchHandler = (ev: ChangeEvent<HTMLInputElement>) =>
    handler(ev.target.value);

  return (
    <InputGroup size="md" {...inputGroupProps}>
      <Input
        pr="4.5rem"
        type="text"
        placeholder="Search"
        onChange={searchHandler}
        value={value}
        {...inputProps}
      />
      <InputRightElement width="4.5rem">
        <Button h="1.75rem" size="sm" {...buttonProps} onClick={handleClick}>
          Clear
        </Button>
      </InputRightElement>
    </InputGroup>
  );
};

const BoxWrap: React.FC<{ wrapping?: BoxProps }> = ({ wrapping, children }) => {
  if (wrapping) return <Box {...wrapping}>{children}</Box>;
  return <>{children}</>;
};

const TbodyComponent: React.FC<{
  tableHeadings: CommonDataTableHeadings<any>[];
  data: any[];
  placeHolderTableRow?: string | ReactElement;
}> = ({ data, tableHeadings, placeHolderTableRow }) => {
  const keys = tableHeadings.map((e) => e.key);

  const rowColumnPlaceholder = () => {
    if (!placeHolderTableRow) return <></>;
    if (typeof placeHolderTableRow === 'string')
      return <Text>{placeHolderTableRow}</Text>;
    if (React.isValidElement(placeHolderTableRow)) return placeHolderTableRow;
  };

  return (
    <>
      {data.map((entry, ei) => {
        return (
          <Tr key={ei}>
            {keys.map((key, ki) => {
              const value = entry[key] ?? rowColumnPlaceholder();
              return <Td key={ki}>{value}</Td>;
            })}
          </Tr>
        );
      })}
    </>
  );
};

const TheadsComponent: React.FC<{
  disableSort?: boolean;
  tableHeadings: CommonDataTableHeadings<any>[];
  sortState: SortState;
  setSortState: Handler<SortState>;
}> = ({ disableSort, tableHeadings, sortState, setSortState }) => {
  return (
    <>
      {tableHeadings.map((th, i) => (
        <ThComponent
          disableSort={disableSort}
          key={i}
          thData={th}
          sortState={sortState}
          setSortState={setSortState}
        />
      ))}
    </>
  );
};

const ThComponent: React.FC<{
  disableSort?: boolean;
  thData: CommonDataTableHeadings<unknown>;
  sortState: SortState;
  setSortState: Handler<SortState>;
}> = ({ disableSort, thData: th, sortState, setSortState }) => {
  const [isDescending, isAscending] = [
    sortState.key === th.key && sortState.order === 'desc',
    sortState.key === th.key && sortState.order === 'asc',
  ];
  return (
    <Th isNumeric={th?.numeric ?? false}>
      <Box display="flex">
        <Text>{th.title ?? th.key}</Text>
        {!disableSort && (
          <Box display="flex" flexDirection="column" ml={1}>
            <TriangleUpIcon
              onClick={() => {
                setSortState({ key: th.key as string, order: 'asc' });
              }}
              boxSize="0.8em"
              cursor="pointer"
              color={isAscending ? 'cyan.700' : 'gray.500'}
            />
            <TriangleDownIcon
              onClick={() =>
                setSortState({ key: th.key as string, order: 'desc' })
              }
              boxSize="0.8em"
              cursor="pointer"
              color={isDescending ? 'cyan.700' : 'gray.500'}
            />
          </Box>
        )}
      </Box>
    </Th>
  );
};

function searchArray<T>(array: T[], value: string) {
  return array.filter((obj) =>
    Object.values(obj).some(
      (val: any) =>
        (val?.toString().toLowerCase().includes(value.toLowerCase()) ||
          value.toLowerCase().includes(val?.toString().toLowerCase()) ||
          value
            .toLowerCase()
            .split(' ')
            .some((split) => val?.toString().toLowerCase().includes(split))) ??
        false,
    ),
  );
}

export default CommonDataTable;
